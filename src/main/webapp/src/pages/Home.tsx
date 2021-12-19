import React, {useEffect, useState} from 'react';
import axios from "axios";
import {ApiUser} from "../api/api-user";
import {useNavigate} from "react-router-dom";

const Home = () => {
    const [isLoaded, setIsLoaded] = useState(false);
    const [users, setUsers] = useState<ApiUser[]>([]);
    const [reloadData, setReloadData] = useState<boolean>(false);
    const navigate = useNavigate();

    const deleteUser = (id: number | undefined) => {
        if (id !== undefined) {
            axios.delete(`http://localhost:8080/users/` + id).then(r => setReloadData(true))
        }
    }

    useEffect(() => {
        axios.get(`http://localhost:8080/users`)
            .then(res => {
                setIsLoaded(true);
                setUsers(res.data);
            })
    }, [reloadData])

    return (
        isLoaded ? (
            <div>
                <h1>Users</h1>
                <button onClick={() => navigate('/create')}>Create new user</button>
                <table>
                    <thead>
                    <tr>
                        <th>Name</th>
                        <th>Email</th>
                        <th>Phone number</th>
                        <th>Age</th>
                        <th>Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    {users.map(user =>
                        <tr>
                            <td>{user.name}</td>
                            <td>{user.email}</td>
                            <td>{user.phoneNumber}</td>
                            <td>{user.age}</td>
                            <td>
                                <button onClick={() => navigate('/edit/' + user.id)}>Edit</button>
                                <button onClick={() => deleteUser(user.id)}>Delete</button>
                            </td>
                        </tr>
                    )}
                    </tbody>
                </table>
            </div>
        ) : (
            <div>Loading...</div>
        )
    );
}
export default Home;