export default {
    state: {
        longitude: "",
        latitude: "",
        projectAddress: "",
    },
    mutations: {
        SET_Longitude(state, data) {
            state.longitude = data;
        },
        SET_Latitude(state, data) {
            state.latitude = data;
        },
        SET_projectAddress(state, data) {
            state.projectAddress = data;
        },
    },
    actions: {

    }
}
