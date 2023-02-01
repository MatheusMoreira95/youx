import axios from 'axios'

export const https = axios.create({
    baseURL: 'https://servicodados.ibge.gov.br/api/v1/localidades/'
})