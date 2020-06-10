<template>
  <div id="blogitem">
      <div v-highlight v-html="htmlContent"></div>
  </div>
</template>

<script>
import Vue from 'vue'
import marked from 'marked'
import hljs from 'highlight.js'
import 'highlight.js/styles/default.css';  //封装成一个指令

Vue.directive('highlight', (el) => {
    let blocks = el.querySelectorAll('pre code')
    blocks.forEach((block) => { 
        hljs.highlightBlock(block) 
    }) 
})

export default {
  name: 'blogitem',
  data() {
    return {
      blog:{},//原始数据
      htmlContent: '',
      blogId: ''
    }
  },
  watch: {
    $route: {
      handler() {
        this.blogId = this.$route.query.id;//获取路由中传过来的参数
        this.$axios.get("/blog/" + this.$route.params.id).then((response) => {
          let blog = response.data.data;
          this.blog = blog;
          this.htmlContent = marked(blog.body)
        })
      }
    },
    //深度监听，同时也可监听到param参数变化
    deep: true,
  },
  mounted() {
    this.$axios.get("/blog/" + this.$route.params.id).then((response) => {
      let blog = response.data.data;
      this.blog = blog;
      this.htmlContent = marked(blog.body)
    })
  },
  created() {
    this.$axios.get("/blog/" + this.$route.params.id).then((response) => {
      let blog = response.data.data;
      this.blog = blog;
      this.htmlContent = marked(blog.body)
    })
  }
}
</script>

<style scoped>  
</style>