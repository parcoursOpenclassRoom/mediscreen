import axios from "axios";

export function listNote() {
    const url = `${process.env.REACT_APP_API_NOTE}/patHistory`;
    return axios.get(url);
}

export function saveNote(data) {
    const url = `${process.env.REACT_APP_API_NOTE}/patHistory`;
    return axios.post(url, data);
}

export function editNote(data) {
    const url = `${process.env.REACT_APP_API_NOTE}/patHistory`;
    return axios.put(url, data);
}

export function removeNote(id) {
    const url = `${process.env.REACT_APP_API_NOTE}/patHistory/${id}`;
    return axios.delete(url);
}

export function getNote(id) {
    const url = `${process.env.REACT_APP_API_NOTE}/patHistory/${id}`;
    return axios.get(url);
}



