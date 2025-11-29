<template>
    <div>

        <!--地图模块-->
        <div v-loading="!show" style="min-height: 500px;">
            <baidu-map v-if="show" scroll-wheel-zoom @ready="mapReady">
                <!--地图视图-->
                <bm-view class="map"></bm-view>

                <!--搜索-->
                <!-- <bm-local-search :keyword="keyword" :panel="isShowPanel" auto-viewport :zoom="10" style="display: none;"
                    :location="location" @markersset="setAddressList"></bm-local-search> -->
                <!-- 定位控件 -->
                <bm-geolocation anchor="BMAP_ANCHOR_BOTTOM_RIGHT" :showAddressBar="true"
                    :autoLocation="true"></bm-geolocation>
                <!-- 自定义控件 -->
                <bm-control>
                    <el-tag type="info" effect="plain">经度：{{ model.storeLongitude }}</el-tag>
                    <el-tag type="info" effect="plain">维度：{{ model.storeLatitude }}</el-tag>
                    <el-tag type="info" effect="plain" v-if="model.address">{{ model.address }}</el-tag>

                </bm-control>
                <!--点标注-->
                <bm-marker-clusterer averageCenter>
                    <bm-marker :position="location" />
                </bm-marker-clusterer>
            </baidu-map>
            <div v-if="show" style="display: flex;justify-content: flex-end;">
                <el-button v-show="showButton" type="primary" icon="el-icon-edit" @click="submit()">确定</el-button>
                <el-button v-show="!showButton" type="primary" style="width: 100%;" icon="el-icon-edit"
                    @click="submit()">获取当前定位</el-button>
                <el-button v-show="showButton" type="message" icon="el-icon-delete"
                    @click="handleClose()">取消</el-button>
            </div>
        </div>

    </div>
</template>

<script>
import BmInfoWindow from "vue-baidu-map/components/overlays/InfoWindow"; //标注弹窗
import BaiduMap from "vue-baidu-map/components/map/Map.vue";
import BmView from "vue-baidu-map/components/map/MapView.vue";
import BmNavigation from "vue-baidu-map/components/controls/Navigation";
import BmMarkerClusterer from "vue-baidu-map/components/extra/MarkerClusterer";
import BmMarker from "vue-baidu-map/components/overlays/Marker";
import BmLocalSearch from "vue-baidu-map/components/search/LocalSearch.vue";

export default {
    props: ["showButton"],
    data() {
        return {
            model: { address: "", storeLongitude: "", storeLatitude: "" },
            location: {
                lng: "",
                lat: ""
            },
            show: false,
            BMap: {},
            map: {},

        }
    },
    components: {
        BaiduMap,
        BmView,
        BmNavigation,
        BmMarkerClusterer,
        BmMarker,
        BmLocalSearch,
        BmInfoWindow,
    },
    methods: {
        mapReady({ BMap, map }) {

            this.BMap = BMap;
            this.map = map;

            let geocoder = new BMap.Geocoder();
            //创建地址解析器的实例
            //第二个参数  10 代表地图缩放级别,最大为19,最小为0
            this.map.centerAndZoom(new BMap.Point(this.location.lng, this.location.lat), 18);

            map.addEventListener("click", ({ point }) => {
                this.location.lng = point.lng;
                this.location.lat = point.lat;
                geocoder.getLocation(point, res => {
                    this.model.address = res.address;
                    console.log("11当前所在位置信息", res);
                    // console.log("当前所在位置信息", this.model.address);
                    this.model.storeLongitude = point.lng
                    this.model.storeLatitude = point.lat
                    this.$forceUpdate();
                });
            });
        },
        getCurrentLocation() {
            var geolocation = new BMap.Geolocation();
            geolocation.getCurrentPosition((res) => {
                this.model.storeLongitude = this.location.lng = res.longitude;
                this.model.storeLatitude = this.location.lat = res.latitude;

                var p = new BMap.Point(res.longitude, res.latitude);
                let geocoder = new BMap.Geocoder();
                geocoder.getLocation(p, (r) => {
                    this.model.address = r.address;
                });
                // console.log(this.model);
                this.show = true;
            });
        },
        handleClose() {
            this.$emit('closeMap');
        },
        submit() {
            this.$store.commit("SET_Longitude", this.model.storeLongitude);
            this.$store.commit("SET_Latitude", this.model.storeLatitude,);
            this.$store.commit("SET_projectAddress", this.model.address);
            this.$emit('getPosition');
            this.$emit('closeMap');
        }
    },
    mounted() {
        this.getCurrentLocation()
    }

}
</script>

<style lang="scss" scoped>
.map {
    width: 100%;
    height: 500px;
}

.el-tag {
    margin: 2px 2px;
}

.el-button {
    margin: 3px 3px;
}
</style>
