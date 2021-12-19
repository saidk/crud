import React, {useEffect, useState} from 'react';
import {ApiUser} from "../api/api-user";
import axios from "axios";
import {useNavigate, useParams} from "react-router-dom";

const Edit = () => {
    const {id} = useParams();

    const [name, setName] = useState<string>('');
    const [email, setEmail] = useState<string>('');
    const [phoneNumber, setPhoneNumber] = useState<string>('');
    const [age, setAge] = useState<number>(0);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`http://localhost:8080/users/` + id)
            .then(res => {
                setName(res.data.name);
                setEmail(res.data.email);
                setPhoneNumber(res.data.phoneNumber);
                setAge(res.data.age);
            })
    }, [id])

    const editUser = () => {
        axios.put(`http://localhost:8080/users`, {
            id: id,
            name: name,
            email: email,
            phoneNumber: phoneNumber
        } as ApiUser).then(_ => navigate('/'))
    }
    return (
        <div>
            <h1>Edit user</h1>
            <div>
                <label>Name:</label>
                <input value={name} onChange={e => setName(e.target.value)} type='text'/>
            </div>
            <div>
                <label>Email:</label>
                <input value={email} onChange={e => setEmail(e.target.value)} type='text'/>
            </div>
            <div>
                <label>Age:</label>
                <input value={age} type='number' disabled/>
            </div>
            <div>
                <label>Phone number:</label>
                <input value={phoneNumber} onChange={e => setPhoneNumber(e.target.value)} type='number'/>
            </div>
            <button onClick={editUser}>Edit</button>
        </div>
    );
}

export default Edit;