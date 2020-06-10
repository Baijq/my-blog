<template>
  <div id="app">
    <!-- 导航 -->
    <navbar/>
    <!-- 组件显示 -->
    <router-view/>
    <!-- 页脚 -->
    <footerbar/>
  </div>
</template>

<script>
import NavBar from './components/NavBar'
import Footer from './components/Footer'

export default {
  name: 'App',
  components: {
    navbar: NavBar,
    footerbar: Footer
  }
}
</script>

<style>
#app{
  width: 100%;
  padding-top: 80px;
  background: url("../src/assets/bg.png")
  /* background: rgba(240, 242, 245, 1); */
  /* background-color: rgba(199, 237, 204, 0.2); */
}
</style>