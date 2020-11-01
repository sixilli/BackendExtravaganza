import axios from 'axios';
import { tournamentModel } from './models/models';

const baseUrl = 'http://127.0.0.1:8000/api'

export const getTournaments = async () => {
    return axios.get(baseUrl + '/tournaments')
};