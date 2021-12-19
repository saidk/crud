import React, {useState} from 'react';
import {ApiUser} from "../api/api-user";
import axios from "axios";
import {useNavigate} from "react-router-dom";

const Create = () => {
    const [name, setName] = useState<string>('');
    const [email, setEmail] = useState<string>('');
    const [phoneNumber, setPhoneNumber] = useState<string>('');
    const navigate = useNavigate();

    const saveUser = () => {
        axios.post(`http://localhost:8080/users`, {
            name: name,
            email: email,
            phoneNumber: phoneNumber
        } as ApiUser).then(_ => navigate('/'))
    }
    return (
        <div>
            <h1>Create user</h1>
            <div>
                <label>Name:</label>
                <input onChange={e => setName(e.target.value)} type='text'/>
            </div>
            <div>
                <label>Email:</label>
                <input onChange={e => setEmail(e.target.value)} type='text'/>
            </div>
            <div>
                <label>Phone number:</label>
                <input onChange={e => setPhoneNumber(e.target.value)} type='number'/>
            </div>
            <button onClick={saveUser}>Save</button>
        </div>
    );
}
export default Create;