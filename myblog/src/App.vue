<script setup>
import { onMounted } from "@vue/runtime-core";
import { useStore } from "vuex";

  const store = useStore()
  
  onMounted:{
    let isLogin = localStorage.getItem('token')
    if(isLogin !=='null' && isLogin){
      
      store.commit('loginJudge',true)
    }
  }
</script>

<template>
  <div>
    <router-view></router-view>
  </div>
</template>

<style>

</style>
