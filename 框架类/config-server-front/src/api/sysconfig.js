import request from '@/utils/request'


export function getSysConfig(data) {
  return request({
    url: '/manager/getSysConfigList',
    method: 'post',
    data
  })
}
