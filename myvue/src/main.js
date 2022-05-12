// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App.vue';
import router from './router/index.js';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import locale from 'element-ui/lib/locale/lang/en'
import axios from "axios";
Vue.config.productionTip = false
Vue.use(ElementUI, { locale,size:'small' })
axios.defaults.baseURL = "http://localhost:8080"
/* eslint-disable no-new */
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')

