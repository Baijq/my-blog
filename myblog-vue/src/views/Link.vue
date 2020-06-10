<template>
  <div id="link">
    <!-- 标题 -->
    <el-card class="box-card" style="font-size:18px;color:#E6A23C;">
      <span>友情链接</span> 
      <el-divider></el-divider>
      <pre style="color:#F56C6C">交换友链可在留言板留言.本站链接如下：
名称：biubiu
网址：https://www.biubiu.cn
图标：https://www.biubiu.cn/logo.png
描述：让坚持成为品质，让优秀成为习惯</pre>
    </el-card>

    <!-- 小格子列表 -->
    <el-card class="box-card" style="margin-top: 20px;overflow:hidden;">
      <el-card class="box-card box-item" shadow="hover" v-for="o in 10" :key="o" style="text-align:center">
        <el-link :underline="false" href="https://www.cnblogs.com/baijinqiang">
          <!-- 头像 -->
          <div>
            <el-avatar :size="80" src="https://note.youdao.com/yws/api/personal/file/74F8E7CAED244D0F86F0866F10267C34?method=download&amp;shareKey=f2cb9362abc0b7613b3d1ec07c46311e"></el-avatar>
          </div>
          <!-- 座右铭 -->
          <span style="display:block;margin:20px 0;color:#303133">让坚持成为品质，让优秀成为习惯</span>
        </el-link>
      </el-card>
    </el-card>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
#link{
  width:80%;
  margin:0 auto;
}
.box-item{
  width: 280px;
  height: 160px;
  float: left;
  margin:20px; 
}
</style>