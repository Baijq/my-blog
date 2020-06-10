<template>
  <div id="footer">
    <!-- 盒子 -->
    <div id="footbox">
      <span>
        <el-link :underline="false" type="info">Copyright © 2020 个人博客 备案号</el-link>
        <el-link :underline="false" target="_blank" href="" type="info">:xxxx</el-link>
      </span>
    </div>

  </div>
</template>

<script>
export default {

}
</script>

<style csoped>
#footer{
  height: 60px;
  width: 100%;
  text-align: center;
  margin-top: 90px;
}
#footbox{
  width: 100%;
  height: 100%;
  background: #30373c;
}
#footbox span {
  display: block;
  margin-top: 23px;
}
</style>