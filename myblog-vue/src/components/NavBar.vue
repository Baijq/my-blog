<template>
  <div id="navbar">
    <el-menu router :default-active="this.$route.path" mode="horizontal" active-text-color="#BC1717" @select="handleSelect">

      <el-menu-item id="logo" index="/addblog">
        BiuBiu
      </el-menu-item>

      <!-- 搜索框 -->
      <el-menu-item>
        <el-input
            placeholder="搜索博客"
            suffix-icon="el-icon-search"
            v-model="searchStr"
            @keyup.enter.native="search">
          </el-input>
      </el-menu-item>

      <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.path" class="bar_item_font">
        {{ item.name }}
      </el-menu-item>

    </el-menu>
  </div>
</template>

<script>
export default {
    name: 'navbar',
    data() {
      return {
        activeIndex: '1',
        searchStr: '',
        navList: [
          {name: '首页', path: '/index'},
          {name: '博客', path: '/blog'},
          {name: '时光轴', path: '/timeline'},
          {name: '留言', path: '/message'},
          {name: '友链', path: '/link'},
          {name: '关于', path: '/about'},

        ]
      };  
    },
    methods: {
      handleSelect(key, keyPath) {
        console.log(key, keyPath);
      },
      search() {
        console.log(this.searchStr);
      }
    }
}
</script>

<style scoped>
#logo{
  margin-left: 3%;
  margin-right: 26%;
  font-family: '华文隶书';
  font-size: 40px;
  color: #BC1717;
}
#navbar{
  position: fixed;
  width: 100%;
  top: 0;
  left: 0;
  z-index: 9999;
  margin-bottom: 80px;
  height: 60px;
}
.bar_item_font{
  font-size: 15px;
  font-weight: 500;
}
.bar_item_font:hover{
  font-size: 15px;
  color: #BC1717 !important;
  transition: all 1s;
}
</style>