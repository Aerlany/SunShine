import Vue from 'vue'
import Vuex from 'vuex'
import getters from './getters'

import navBar from "./modules/navBar";
import user from "./modules/user";
import project from "./modules/project";
import map from "./modules/map";


Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {
        navBar,
        user,
        project,
        map
    },
    getters
})

export default store