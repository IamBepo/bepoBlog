<template>
    <div class="heatContainer">
        <div style="font-size: 20px;color: #4d4f50;border-bottom: solid 1px #efd4d4;">
            <div style="border-bottom: solid 2px red;width:fit-content;">
                推荐
            </div>
        </div>

        <div style="margin-top: 10px;">
            <div class="content" style="display: flex;align-items: center;margin: 5px 0;" v-for="(item,index) in list" :key="index" @click="heatFun(item.id)">
                <div style="margin-right: 10px;height: 60px;">
                    <img style="width: 80px;height: 60px;" :src="item.cover"/>
                </div>
                <div class="textS">
                    {{item.title}}
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from "@vue/reactivity";
import {useRouter} from 'vue-router'
    let router = useRouter()

    defineProps({
        list:Object
    })

    function heatFun(value) {
        let routeUrl = router.resolve({
            path: `/read/${value}`
        })
        window.open(routeUrl.href, '_blank');
    }

</script>

<style scoped>
    .heatContainer {
        box-shadow: 0 0 4px 0 rgb(0 0 0 / 20%);
        border-radius: 5px;
        padding: 20px;
        margin-top: 10px;
        transition: all .3s linear;
        background-color: white;
    }
    .heatContainer:hover {
        box-shadow: 0 0 8px 0 rgb(0 0 0 / 20%);
        -webkit-box-shadow:  0 0 15px 0 rgb(0 0 0 / 20%);
        -webkit-transition: all .5s ease-out;
        transition: all .5s ease-out;
    }
    .el-divider--horizontal {
        margin: 5px 0;
    }
    .textS {
        width: 80%;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
        overflow: hidden;

        color: #535454;
        font-size: 15px;
    }
    .textS:hover {
        color: #8f3535;
        cursor: pointer;
    }

    .content:hover {
        cursor: pointer;
        background-color: #f8f9f9;
    }
</style>