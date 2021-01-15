import axios from 'axios';

const baseUrl = 'http://127.0.0.1:5000/';

const blogEndpoint = 'blog';
const tournamentEndpoint = 'tournament';

const get = async (endpoint: string) => {
    return axios.get(baseUrl + endpoint);
};

const getSingle = async (endpoint: string, id: number) => {
    return axios.get(baseUrl + endpoint + '/' + id);
};

export const getTournaments = async () => {
    return await get(tournamentEndpoint);
};

export const getTournament = async (id: number) => {
    return await getSingle(tournamentEndpoint, id);
};

export const getBlogPosts = async () => {
    return await get(blogEndpoint);
};

export const getBlogPost = async (id: number) => {
    return await getSingle(blogEndpoint, id);
};