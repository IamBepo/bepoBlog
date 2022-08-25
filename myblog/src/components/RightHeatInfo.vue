<template>
    <div class="heatContainer">
        <div style="font-size: 20px;color: #4d4f50;border-bottom: solid 1px #efd4d4;">
            <div style="border-bottom: solid 2px red;width:fit-content;">
                推荐
            </div>
        </div>

        <div style="margin-top: 10px;">
            <div class="content" style="display: flex;align-items: center;margin: 5px 0;" v-for="(item,index) in heatData" :key="index" @click="heatFun(item.title)">
                <div style="margin-right: 10px;height: 60px;">
                    <img style="width: 80px;height: 60px;" :src="item.imgurl"/>
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


let heatData = ref([
    {
        title: '《Nigella靓太拿手菜》EP05 - 焦糖布甸 辣酱意大利粉 番茄酱肉丸配羽衣甘蓝薯茸',
        imgurl: 'http://up.llxuo.top/autospoofingAPP/c3af8ce6-7c52-48b8-8059-b7162e5cdffe.jpg'
    },
    {
        title: '《Nigella靓太拿手菜》EP05 - 焦糖布甸 辣酱意大利粉 番茄酱肉丸配羽衣甘蓝薯茸',
        imgurl: 'http://up.llxuo.top/autospoofingAPP/c3af8ce6-7c52-48b8-8059-b7162e5cdffe.jpg'
    },
    {
        title: 'springboot啊啊啊',
        imgurl: 'http://up.llxuo.top/autospoofingAPP/c3af8ce6-7c52-48b8-8059-b7162e5cdffe.jpg'
    }
])

function heatFun(value) {
    console.log(value + "被点击")
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