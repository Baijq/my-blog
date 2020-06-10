<template>
  <div id="blog">
    <!-- 布局容器 -->
    <el-container v-show="listShow">

      <!-- 左边区域 -->
      <el-main width="70%" style="padding:0">
        <!-- 文章列表区域 -->
        <el-card class="box-card" shadow="never">
          
          <!-- 每一条文章 -->
          <el-card class="box-card blogitem" shadow="hover" 
            v-for="blog in bloglist" :key="blog.id">
            <el-container>
              <el-aside width="200px" style="cursor:pointer;background:#fff">
                <router-link :to="{path:'/blog/detail/'+blog.id}">
                  <el-image :src="blog.imgUrl"></el-image>
                </router-link>
              </el-aside>
              <el-main style="height:176px;background:#fef;">
                <router-link :to="{path:'/blog/detail/'+blog.id}">
                    <h3>{{blog.title}}</h3>
                </router-link>
                <router-link :to="{path:'/blog/detail/'+blog.id}">
                    <p style="font-size:14px;color:#909399;">{{blog.body.substring(0, 60)}}</p>
                </router-link>
                <div>
                  <span><i class="fa fa-clock-o" style="color:red"></i>&nbsp;{{blog.time}}</span>&nbsp;&nbsp;
                  <span><i class="fa fa-user-circle-o" style="color:#e6a23c"></i>&nbsp;{{blog.user.nickName}}</span>                    
                </div>
              </el-main>
            </el-container>
          </el-card>

          <!-- 分页 -->
          <div class="block">
            <el-pagination
              :hide-on-single-page="true"
              background
              layout="prev, pager, next, jumper, total"
              :total="1000">
            </el-pagination>
          </div>
        </el-card>
      </el-main>

      <!-- 右边区域 -->
      <el-aside width="30%" style="padding-left:10px">

        <!-- 右侧box1 -->
        <el-card class="box-card right-box-card" style="text-align:center">
          <!-- 头像logo -->
          <div>
            <el-avatar :size="100" src="https://note.youdao.com/yws/api/personal/file/74F8E7CAED244D0F86F0866F10267C34?method=download&amp;shareKey=f2cb9362abc0b7613b3d1ec07c46311e"></el-avatar>
          </div>

          <!-- 座右铭 -->
          <span style="display:block;margin:20px 0;color:#303133">让坚持成为品质，让优秀成为习惯</span>

          <!-- qq/weixin/github/email -->
          <el-link :underline="false" href="tencent://message/?uin=1819939081&amp;Site=&amp;Menu-=yes" class="fa fa-qq fa-2x my_padding" style="color:#E6A23C"></el-link>
          <el-link :underline="false" href="mailto:1819939081@qq.com" class="fa fa-envelope fa-2x my_padding" style="color:#F56C6C"></el-link>
          <el-link :underline="false" target="_blank" href="https://github.com/baijq" class="fa fa-github fa-2x my_padding" style="color:#000000"></el-link>
          <el-tooltip class="item" effect="dark" content="微信：bjq611159" placement="top" :open-delay="500">
            <el-link :underline="false" class="fa fa-weixin fa-2x my_padding" style="color:#67C23A"></el-link>
          </el-tooltip>

          <!-- 欢迎留言按钮 -->
          <router-link to='/message'>
            <el-button type="success" style="width:90%" size="medium" round>欢迎留言</el-button>
          </router-link>
        </el-card>
        
        <!-- 标签盒子 -->
        <el-card class="box-card right-box-card">
          <span><i class="fa fa-tags"></i>&nbsp;我的标签</span> 
          <el-divider></el-divider>
          <el-tag type="success" style="margin: 5px" v-for="tag in tagList" :key="tag.id">{{tag.name}}</el-tag>
          <!-- <el-divider></el-divider>
          <el-badge :value="1" class="item" type="primary" style="margin: 5px" v-for="tag in tagList" :key="tag.id">
            <el-button size="small">{{tag.name}}</el-button>
          </el-badge> -->
        </el-card>

        <!-- 标签盒子 -->
        <el-card class="box-card right-box-card">
          <span><i class="fa fa-pencil"></i>&nbsp;博客分类</span>
          <el-divider></el-divider>
          <el-link :underline="false">青春是一个短暂的美梦</el-link>
          <el-divider></el-divider>
          <el-link :underline="false">少量的邪恶足以抵消全部高贵的品质</el-link>
        </el-card>


        <el-card class="box-card right-box-card">
          <el-table
            :data="tableData"
            style="width: 100%">
            <el-table-column
              prop="address"
              label="地址">
            </el-table-column>
          </el-table>
        </el-card>


      </el-aside>
      
    </el-container>


    <!-- 博客detail展示 -->
    <el-card class="box-card" shadow="never" v-show="detailShow" style="width:80%;margin:0 auto;">
      <router-view></router-view>
    </el-card>

  </div>
</template>

<script>
export default {
  data () {
    return {
      bloglist: [],
      tagList:[],
      src: 'https://picsum.photos/id/1005/280/200',
      detailShow: false,//博客详情一开始影藏
      listShow: false,//博客列表，显示
      tableData: [{
            address: '上海市普陀区金沙江路 1518 弄'
          }, {
            address: '上海市普陀区金沙江路 1517 弄'
          }, {
            address: '上海市普陀区金沙江路 1519 弄'
          }, {
            address: '上海市普陀区金沙江路 1516 弄'
          }]
    }
  },
  methods: {
    
  },
  watch: {
    $route: {
      handler() {
        //路由是否包含detail
        if(this.$route.path.indexOf('detail') != -1 ) {
          this.detailShow = true;
          this.listShow = false;
        } else{
          this.detailShow = false;
          this.listShow = true;
        }
      }
    },
    //深度监听，同时也可监听到param参数变化
    deep: true,
  },
  created () {
    // this.$axios.get("http://localhost:8080/posts.json")
    // .then(res=>{
    //   let data = res.data.slice(0,10); //取10条测试数据
    //   this.bloglist = data;
    // });
    //获取博客列表
    this.$axios.get("/blog")
    .then(res=>{
      this.bloglist = res.data.data;
    })
    //路由选则
    if(this.$route.path.indexOf('detail') != -1 ) {
      this.detailShow = true;
      this.listShow = false;
    } else{
      this.detailShow = false;
      this.listShow = true;
    }
    //获取右侧tag
    this.$axios.get("/tag").then((response) => {
      this.tagList = response.data.data;
    })
    
  }
}
</script>

<style scoped>
#blog{
  width: 90%;
  margin: 0 auto;
}
.blogitem{
  width:100%; 
  height:214px;
  margin-bottom: 20px;
  border-radius: 0;
  background:rgba(248, 249, 250, 1);
}
.right-box-card{
  margin-bottom: 20px;
}

.my_padding{
  padding:0 15px;
  margin-bottom: 20px;
}

</style>