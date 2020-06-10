<template>
  <div id="addblog">

    <!-- 录入博客的注意事项 -->
    <div id="note">
      <el-card class="box-card" shadow="never">
        <h5 style="margin: 5px 20px 15px 0;">写作时温馨提示</h5>
        <div class="tip">
          <span class="text">
            对于不正当留言，会删除处理。<br>
            1. 欢迎各种留言，技术交流、生活趣事、小说、电影、护肤、以及各种吐槽......<br>
            2. 忌不正当留言，辱骂、不健康、不文明等危险言论。<br>
            做社会主义新时代好青年！！！<br>
          </span>
        </div>
      </el-card>
    </div>

    <el-card class="box-card" shadow="never"> 
      <el-form :model="blog" ref="blog" label-width="80px" label-position="top" :rules="rules">
        <!-- title -->
        <el-form-item style="margin-botton:-15px">
            <el-input placeholder="标题" v-model="blog.title" prop="title">
                <template slot="prepend">原创</template>
            </el-input>
        </el-form-item>

        <!-- content -->
        <el-form-item>
          <div id="editor">
            <mavon-editor style="height:400px" ref="editor" v-model="blog.context"
              @imgAdd="$imgAdd" @imgDel="$imgDel"></mavon-editor>
          </div>
        </el-form-item>

        <!-- 标签 -->
        <el-form-item>
            <el-select v-model="blog.tags" multiple placeholder="标签" style="width:100%">
              <el-option
              v-for="item in tagList"
              :key="item.id"
              :label="item.name"
              :value="item.id">
              </el-option>
            </el-select>
        </el-form-item>

        <!-- 首页图片引用地址 -->
        <el-form-item>
            <el-input placeholder="首页图片引用地址" v-model="blog.picUrl">
                <template slot="prepend">首页图片引用地址</template>
            </el-input>
        </el-form-item>

        <el-form-item>
            <el-button type="warning" plain @click="commit" style="width:100%">发布</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
//引入markdown的支持
import { mavonEditor } from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'

export default {
  name: 'AddBlog',
  data() {
    return {
      blog: {
        title: '',
        context: '',
        tags: [],
        picUrl: '',
      },
      tagList: [],
      rules: {
        title: [
          { required: true, message: '请输入标题', trigger: 'blur' },
          { min: 1, max: 80, message: '长度在 1 到 80 个字符', trigger: 'blur' }
        ]
      }
    }
  },
  components: {
    mavonEditor,
  },
  methods: {
    // 添加博客
    commit() {
      // console.log(this.blog)
      this.$axios.post("/blog",JSON.stringify(this.blog),{headers: {'Content-Type': 'application/json;charset=UTF8'}})
      .then((res) => {
        // console.log(res.data)
        if(res.data.success) {
          this.$alert('博客已经添加成功！', '注意', {
            confirmButtonText: '确定',
            callback: action => {
              this.$message({
                type: 'info',
                message: `action: ${ action }`
              });
              this.$router.push({path:'/blog'});
            }
          });
        }else{
          this.$alert(res.data.message, '失败提示', {
            confirmButtonText: '确定',
            callback: action => {
              this.$message({
                type: 'info',
                message: `action: ${ action }`
              });
            }
          })
        }
      })
    },
    //上传图片 绑定@imgAdd event
    $imgAdd(pos, $file){
      // 第一步.将图片上传到服务器.
      var file = new FormData();
      file.append('file', $file);
      this.$axios({
        url: '/blog/uploadImg',
        method: 'post',
        data: file,
        headers: {'Content-Type': 'multipart/form-data'}
      }).then((res)=>{
        let url = res.data.data;
        console.log(url)
        // 第二步.将返回的url替换到文本原位置![...](0) -> ![...](url)
        // $vm指为mavonEditor实例，可以通过如下两种方式获取
        //通过引入对象获取: import {mavonEditor} from ... 等方式引入后，此时$vm即为mavonEditor
        //通过$refs获取: html声明ref : <mavon-editor ref=md ></mavon-editor>， 此时$vm为 this.$refs.md`
        this.$refs.editor.$img2Url(pos, url);
        // $vm.$img2Url(pos, url);
      })

    },
    //删除图片
    $imgDel(pos) {
        delete this.img_file[pos];
    }
  },
  created() {
    this.$axios.get("/tag").then((response) => {
      this.tagList = response.data.data;
    })
  }
}
</script>

<style scoped>
#addblog{
  width: 70%;
  margin: 0 auto;
}
.tip{
  border: none;
  border-left: 4px solid rgba(183, 23, 23, 0.8);
  background-color: rgba(237, 239, 240, 0.5);
  padding: 10px 15px;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  font-size: 13px;
}
.text{
  font-size: 14px;
  letter-spacing: 1px;
  line-height: 26px;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  color: #606266;
  font-weight: 500;
}
#note{
  margin-bottom: 20px;
}

.el-form-item {
    margin-bottom: 10px;
}
</style>