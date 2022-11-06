<script setup>
import { inject, onMounted } from "@vue/runtime-core";
import { useStore } from "vuex";
import systemApi from '@/api/SystemApi'

  const store = useStore()

  function isDeviceMobile() {
    var u = navigator.userAgent
    var isAndroid = u.indexOf('Android') > -1 || u.indexOf('Adr') > -1 //android终端
    var isiOS = !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/) //ios终端
    if (isiOS || isAndroid) {
      return true
    } else {
      return false
    }
  }

  
  onMounted:{
    let token = localStorage.getItem('token')
    if(token !=='null' && token){
      systemApi.getAuthorizationRouter(token).then(res => {
        console.log(res.data)
        store.commit('loginJudge',true)
      })
    }
  }
</script>

<template>
  <div :style="[{ minWidth: !isDeviceMobile() ? '890px' : 0 }]">
    <router-view></router-view>
  </div>
</template>

<style>

</style>
