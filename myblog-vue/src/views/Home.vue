<template>
  <div id="home">
    
    <el-carousel indicator-position="outside">
      <el-carousel-item v-for="item in imgUrls" :key="item">
        <el-image
        :src="item"
        :fit="imagefit"></el-image>
      </el-carousel-item>
    </el-carousel>

    <div id="zhanwei">
    </div>

  </div>
</template>

<script>

export default {
  name: 'Home',
  data() {
    return{
      imagefit: 'fill',
      imgUrls: [
        "https://picsum.photos/id/1043/1920/700",
        "https://picsum.photos/id/1045/1920/700",
        "https://picsum.photos/id/1055/1920/700",
        "https://picsum.photos/id/128/1920/700",
        "https://picsum.photos/id/1051/1920/700",
        "https://picsum.photos/id/176/1920/700"
      ],
    }
  },
  components: {
    
  }
}
</script>

<style scoped>
#zhanwei{
  height: 51px;
}
</style>
