const apiURL = 'http://localhost:8090/'
import axios from "axios";
export const func_global = {

    importData(requestId, myfile, token) {
        // let myfile = this.$refs.myfile;
        let files = myfile.files;
        let file = files[0];
        var formData = new FormData();
        formData.append("file", file);
        this.uploadFileRequest(formData, requestId, token)
    },

    uploadFileRequest (data, requestId, token) {
        const url = `${apiURL}role-request/import/${requestId}/`
        // const token = this.$store.getters.getToken;
        axios.post(url, data, {headers: {Authorization: `Bearer ${token}`, 'Content-Type': 'multipart/form-data'}}).then((response) => {
            console.log(response.data)
        }).catch(error => {
            console.log(error.response)
        });
    },
}