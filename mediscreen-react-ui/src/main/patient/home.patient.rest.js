import axios from "axios";

export function listPatient() {
    const url = `${process.env.REACT_APP_API_PATIENT}/patient`;
    return axios.get(url);
}

export function savePatient(data) {
    const url = `${process.env.REACT_APP_API_PATIENT}/patient`;
    return axios.post(url, data);
}

export function editPatient(data) {
    const url = `${process.env.REACT_APP_API_PATIENT}/patient`;
    return axios.put(url, data);
}

export function removePatient(id) {
    const url = `${process.env.REACT_APP_API_PATIENT}/patient/${id}`;
    return axios.delete(url);
}

export function getPatient(id) {
    const url = `${process.env.REACT_APP_API_PATIENT}/patient/${id}`;
    return axios.get(url);
}

export function reportPatient(id) {
    const url = `${process.env.REACT_APP_API_REPORT}/assess/id?patId=${id}`;
    return axios.post(url, {});
}



