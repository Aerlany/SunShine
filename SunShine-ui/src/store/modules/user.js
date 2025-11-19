import { login, logout, getInfo } from '@/api/login'
import { getToken, setToken, removeToken, setUserId, getUserId, setUserName, getUserName, getPermission } from '@/utils/auth'
import { Message } from 'element-ui';

export default {
  state: {
    token: getToken(),
    userId: getUserId(),
    userName: getUserName(),
    permission: getPermission(),
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_ID: (state, id) => {
      state.id = id
    },
    SET_USERID: (state, userInfo) => {
      state.userId = userInfo
    },
    SET_USERNAME: (state, userInfo) => {
      state.userName = userInfo
    },
  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      const userName = userInfo.userName.trim()
      const password = userInfo.password
      return new Promise((resolve, reject) => {
        login(userName, password).then(res => {
          const { flag, data, msg } = res;
          if (flag) {
            setToken(msg)
            setUserId(data.id);
            setUserName(data.userName);
            commit('SET_TOKEN', msg);
            commit('SET_USERID', data.id);
            commit('SET_USERNAME', data.userName);
            resolve()
          } else {
            Message.error(msg);
            reject();
          }
        }).catch(error => {
          Message.error('登录失败');
          reject(error)
        })
      })
    },

    // 退出系统
    LogOut({ commit, state }) {
      return new Promise((resolve, reject) => {
        logout(state.token).then((res) => {
          const { flag, data, msg } = res;

          commit('SET_TOKEN', '')
          //当前用户权限信息
          // commit('SET_ROLES', [])
          // commit('SET_PERMISSIONS', [])
          removeToken();
          localStorage.clear();
          resolve()

        }).catch(error => {
          reject(error)
        })
      })
    },

    // 前端 登出
    FedLogOut({ commit }) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        removeToken()
        resolve()
      })
    }
  }
}
