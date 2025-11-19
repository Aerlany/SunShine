
export default {
    // namespaced: true,
    state: {
        isCollapse: true,

        topBarDisPlayTags: [],
        currentProject: "",
        currentFunction: ""
    },
    mutations: {
        SET_ISCOLLAPSE(state) {
            state.isCollapse = !state.isCollapse;
        },
        SET_CURRENTPROJECT(state, data) {
            state.currentProject = data;
        },
        RESET_topBarDisPlayTags(state) {
            state.topBarDisPlayTags = [];
        },
        PUSH_RESET_topBarDisPlayTags(state, data) {
            state.topBarDisPlayTags.push(data);
        },
        SET_CURRENTFUNCTION(state, data) {
            state.currentFunction = data;
        }
    },
    actions: {

    }
}
