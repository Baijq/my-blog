import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import 'font-awesome/css/font-awesome.css'//字体图标库
import 'normalize.css/normalize.css'//样式初始化

Vue.use(ElementUI);
Vue.use(VueAxios, axios)

// 将Axios挂载到Vue上
Vue.prototype.$axios = axios
axios.defaults.baseURL='http://localhost:8888';

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
