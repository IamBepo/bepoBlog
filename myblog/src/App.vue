<script setup>
import { inject, onMounted } from "@vue/runtime-core";
import { useStore } from "vuex";

  const store = useStore()
  const axios = inject('$axios')
  const qs = inject('$qs')
  
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
