import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { BatteryService } from './services/BatteryService';

const AddBattery = () => {
    let [state, setState] = useState({
        loading: false,
        battery: {
            name: '',
            postcode: '',
            capacity: '',
        },
        errorMessage: ''
    });

    let updateInput = (e) => {
        setState({
            ...state,
            battery: {
                ...state.battery,
                [e.target.name]: e.target.value
            }
        })
    };

    let navigate = useNavigate();

    const submitForm = async (e) => {
        e.preventDefault();
        console.log('form submitting');
        try {
            BatteryService.createBattery(battery);
            navigate('/', { replace: true })
        } catch (error) {
            console.log(error);
            setState({ ...state, errorMessage: error.message })
            navigate('/battery/add', { replace: false });
        }
    }

    let { loading, battery, errorMessage } = state;
    return (
        <>
            <section className="add-battery">
                <div className="container">
                    <div className="row">
                        <div className="col">
                            <p className="h3 text-success fw-bold">Add Battery</p>
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-md-4">
                            <form onSubmit={submitForm}>
                                <div className="mb-2">
                                    <input type="text"
                                        name="name"
                                        onChange={updateInput}
                                        value={battery.name}
                                        required={true}
                                        className='form-control' placeholder='Name' />
                                </div>
                                <div className="mb-2">
                                    <input type="text"
                                        name='postcode'
                                        onChange={updateInput}
                                        value={battery.postcode}
                                        className='form-control' placeholder='Postcode' />
                                </div>
                                <div className="mb-2">
                                    <input type="text"
                                        name='capacity'
                                        onChange={updateInput}
                                        value={battery.capacity}
                                        className='form-control' placeholder='Capacity' />
                                </div>
                                <div className="mb-2">
                                    <button type="submit" className="btn btn-success mx-2" >Submit</button>
                                    <Link to={'/'} className='btn btn-dark'>Cancel</Link>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </>
    )
}

export default AddBattery;