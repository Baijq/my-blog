import Vue from 'vue'
import VueRouter from 'vue-router'

//引入组件,异步加载
const Home = () => import('../views/Home.vue') //首页
const Blog = () => import('../views/Blog.vue') //博客
const Timeline = () => import('../views/Timeline.vue') //时间线
const Message = () => import('../views/Message.vue') //留言
const Link = () => import('../views/Link.vue') //留言
const About = () => import('../views/About.vue') //关于

const AddBlog = () => import('../views/AddBlog.vue')//添加博客
const BlogItem = () => import('../components/BlogItem.vue')

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    redirect: '/index'
  },
  {
    path: '/index',
    name: 'Home', //首页
    component: Home
  },
  {
    path: '/blog',
    name: 'Blog',//博客
    component: Blog,
    children:[
      {path:'detail/:id',component: BlogItem}
    ]
  },
  {
    path: '/timeline',
    name: 'Timeline',//时光轴
    component: Timeline
  },
  {
    path: '/message',
    name: 'Message',//留言
    component: Message
  },
  {
    path: '/link',
    name: 'Link',//友链
    component: Link
  },
  {
    path: '/about',
    name: 'About',//关于
    component: About
  },

  {
    path: '/addblog',
    name: 'AddBlog',//点击logo,跳转添加博客页面
    component: AddBlog
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
