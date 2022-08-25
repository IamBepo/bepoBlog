<template>
    <div style="display: flex;flex-direction: column;min-height: 100vh;">
        <div style="flex: 0;position: fixed;top: 0;width: 100%;background-color: white;z-index: 999">
            <TopNav></TopNav>
        </div>
        <div class="midMajor">
            <router-view></router-view>
        </div>
        <div style="flex: 0;">
            <LastStatement></LastStatement>
        </div>
    </div>
</template>

<script>
import {ref} from 'vue'
import TopNav from '../components/TopNav.vue'
import LastStatement from '../components/LastStatement.vue'
export default{
  components: { TopNav, LastStatement },
    name:'View',
    setup(){
        let name = ref('哈哈哈哈')

        return {
            name
        }
    }
}
</script>

<style scoped>
    .midMajor {
        flex: 1;
        background-color: #F4F5F8;
        /* background: url("../assets/back.jpg") no-repeat center center fixed; */
        margin-top: 60px;
    }
</style>>