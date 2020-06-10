<template>
  <div id="message">
    <!-- 提示card -->
    <el-card class="box-card" shadow="never">
      <h3>提示</h3>
      <div class="tip">
        <span class="text">
          对于不正当留言，会删除处理。<br>
          1. 欢迎各种留言，技术交流、生活趣事、小说、电影、护肤、以及各种吐槽......<br>
          2. 忌不正当留言，辱骂、不健康、不文明等危险言论。<br>
          做社会主义新时代好青年！！！<br>
        </span>
      </div>
      <!-- 图标 -->
      <div>
        <el-link class="icontext" :underline="false"><i class="fa fa-qq fa-lg"></i> 1819939081</el-link>
        <el-link class="icontext" :underline="false"><i class="fa fa-envelope fa-lg"></i>&nbsp;   baijqmail@163.com</el-link>
      </div>
    </el-card>

    <!-- formCard -->
    <el-card class="box-card" shadow="never" style="margin-top: 5px">
      <div slot="header" class="clearfix">
        <span>留言信息</span>
      </div>

      <el-form :model="messageForm" ref="messageForm" label-width="100px" class="demo-dynamic">
        <el-form-item prop="email" label="邮箱" :rules="[
            { required: true, message: '请输入邮箱地址', trigger: 'blur' },
            { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
          ]">
          <el-input placeholder="请输入邮箱！" v-model="messageForm.email"></el-input>
        </el-form-item>

        <el-form-item label="内容" prop="context" :rules="[{ required: true, message: '请填写活动形式', trigger: 'blur' }]">
          <el-input
            type="textarea"
            :rows="4"
            placeholder="请输入您的留言内容！"
            v-model="messageForm.context">
          </el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="success" plain @click="submitForm('messageForm')" style="width:100%">提交</el-button>
        </el-form-item>
      </el-form>
    </el-card>

    <div id="preview" style="text-align:center">
      <h3>留言信息</h3>
      <p>留言留言留言留言留言留言留言留言留言留言留言留言留言留言</p>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return{
      messageForm: {
        context: '',
        email: ''
      }
    }
  },
  methods: {
    submitForm(messageForm) {
      this.$refs[messageForm].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(messageForm) {
      this.$refs[messageForm].resetFields();
    },
  }
}
</script>

<style scoped>
#message{
  width: 80%;
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
.icontext{
  font-size: 15px;
  padding-top: 20px;
  margin-right: 40px;
  color: rgba(183, 23, 23, 0.6);
}
#preview{
  width: 96%;
  padding: 10px 20px;
  border: 1px dotted #ccc;
  margin: 10px 0;
}
</style>