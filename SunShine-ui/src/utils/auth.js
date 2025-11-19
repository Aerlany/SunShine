import Cookies from 'js-cookie'

const TokenKey = 'Token'
const userIdKey = 'USER_INFOMARION_ID'
const userNameKey = 'USER_INFOMARION_NAME'
const permission = 'permission'


export function getToken() {
  return Cookies.get(TokenKey)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}

export function setUserId(data) {
  return Cookies.set(userIdKey, data)
}

export function getUserId() {
  return Cookies.get(userIdKey)
}
export function setUserName(data) {
  return Cookies.set(userNameKey, data)
}

export function getUserName() {
  return Cookies.get(userNameKey)
}

export function getPermission() {
  return Cookies.get(permission)
}

export function setPermission(data) {
  return Cookies.set(permission, data)
}