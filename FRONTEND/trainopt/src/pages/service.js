import httpClient from './http-common';
class service{
    Signup(datainfo){
        return httpClient.post("/users/createUser",datainfo);
    }
    getUser(email){
        return httpClient.get(`/users/findUserByMail/${email}`);
    }
    fetchTrains(from , to , date)
    {
        return httpClient.get(`/trainSearch/getTrains/getExactTrain/${date}/${from}/${to}`);
    }
    gettheTrain(id)
    {
        return httpClient.get(`/trainSearch/getById/${id}`);
    }
    getduplicateusers(email)
    {
        return httpClient.get(`/users/findDuplicateUsers/${email}`);
    }
    updateUserByEmail(email,data)
    {
        return httpClient.put(`/users/updateUserByMail/${email}`,data);
    }
    savePassenger(data)
    {
        return httpClient.post("/passenger/createPassenger",data);
    }
    removeOnepassenger(id)
    {
        return httpClient.delete(`/passenger/deletePassenger/${id}`);
    }
    getAllPass()
    {
        return httpClient.get("/passenger/getAll");
    }
    removeAll()
    {
        return httpClient.delete("/passenger/removeAll");
    }getduplicateusers(email)
    {
        return httpClient.get(`/users/findDuplicateUsers/${email}`);
    }
    updateUserByEmail(email,data)
    {
        return httpClient.put(`/users/updateUserByMail/${email}`,data);
    }
    savePassenger(data)
    {
        return httpClient.post("/passenger/createPassenger",data);
    }
    removeOnepassenger(id)
    {
        return httpClient.delete(`/passenger/deletePassenger/${id}`);
    }
    getAllPass()
    {
        return httpClient.get("/passenger/getAll");
    }
    removeAll()
    {
        return httpClient.delete("/passenger/removeAll");
    }
}
export default new service();