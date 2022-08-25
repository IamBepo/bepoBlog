<template>
  <el-carousel :interval="5000" arrow="hover" v-if="list">
    <el-carousel-item v-for="(item,index) in list" :key="index">
      <div style="text-align: center;height: 100%;">
        <img :src="item.cover" style="height:100%"/>
      </div>
      <h3 text="2xl" justify="center">{{ item.title }}</h3>
    </el-carousel-item>
  </el-carousel>
</template>

<script setup>

  defineProps({
    list:Object
  })

</script>


<style scoped>
.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
</style>