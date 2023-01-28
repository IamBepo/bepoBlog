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

<script setup>
import {onMounted, ref} from 'vue'
import TopNav from '../components/TopNav.vue'
import LastStatement from '../components/LastStatement.vue'
import store from '../store'
import systemApi from '../api/SystemApi'

    function getSystemInfo() {
        systemApi.getBlogInfo().then(res => {
            store.commit('getSystemInfo', res.data.data)

            let $favicon = document.querySelector('link[rel="icon"]');
            if ($favicon !== null) {
                $favicon.href = res.data.data.webLogo;
            } else {
                $favicon = document.createElement("link");
                $favicon.rel = "icon";
                $favicon.href = res.data.data.webLogo;
                document.head.appendChild($favicon);
            }
        })
    }

    onMounted:{
        getSystemInfo()
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