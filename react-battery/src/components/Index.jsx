import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import Loader from './Loader';
import { BatteryService } from './services/BatteryService';

const IndexPage = () => {
    let [state, setState] = useState({
        loading: false,
        battery:[],
        averageCapacity:0,
        countCapacity:0,
        maxCapacity:0,
        minCapacity:0,
        totalCapacity:0,
        batteryFilterPojo: {
            from: 0,
            to: 0
        },
        errorMessage: ''
    });

    let updateInput = (e) => {
        setState({
            ...state,
            batteryFilterPojo: {
                ...state.batteryFilterPojo,
                [e.target.name]: e.target.value
            }
        })
    };

    const submitForm = async (e) => {
        e.preventDefault();
        console.log('form submitting');
        try {
            setState({ ...state, loading: true })
                let response = await BatteryService.getBatteryFromRangeBattery(batteryFilterPojo);
                console.log("The data",response.data.data.batteryList)
                setState({
                    ...state,
                    loading: false,
                    battery: response.data.data.batteryList,
                    averageCapacity: response.data.data.averageCapacity,
                    totalCapacity: response.data.data.totalCapacity,
                    minCapacity: response.data.data.minCapacity,
                    maxCapacity: response.data.data.maxCapacity,
                    countCapacity: response.data.data.countCapacity,
                })
            
        } catch (error) {
            setState({
                ...state,
                loading: false,
                errorMessage: error.message
            })
            
        }
    }
    
    


    //destructuring the data set into current state from api
    let { loading, batteryFilterPojo, errorMessage,battery,averageCapacity,totalCapacity,minCapacity,maxCapacity,countCapacity } = state;

    return (
        <>
            <section>
                <div className="container">
                    <div className="grid">
                        <div className="row">
                            <div className="col">
                                <p className="h3 fw-bold">
                                    Battery Management
                                    <Link to={'/battery/add'} className="btn btn-primary ms-2" >
                                        <i className='fa fa-plus-circle me-2' />New </Link>
                                </p>
                                <p className="fst italic">Filtere Postcode</p>
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-md-6">
                                <form className='row' onSubmit={submitForm}>
                                    <div className="col">
                                        <div className="mb-2">
                                            <input type="number" className="form-control" name="from" onChange={updateInput} placeholder='From Postcode range' />
                                        </div>
                                        <div className="mb-2">
                                            <input type="number" className="form-control" name="to" onChange={updateInput} placeholder='To Postcode range' />
                                        </div>
                                    </div>
                                    <div className="col">
                                        <div className="mb-2">
                                            <button type='submit' className="btn btn-outline-dark"> Search </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            {
                loading ? <Loader /> :
                    <>
                        <section className="battery-list">
                            <div className="container">
                                <div className="row">
                                <div className="col-md-6 mt-2" >
                                                    <div className="card">
                                                    <div className="row card-header">
                                                        <h1>List of batteries</h1>
                                                        </div>
                                            <div className="row card-body align-item-center">
                                                            <div className="row">
                                                                
                                                                <div className="col-md-12">
                                                                {
                                        battery.length > 0 && battery.map(c => {
                                            return (
                                                
                                                                    <ul className="list-group">
                                                                        <li className="list-group-item list-group-item-event">
                                                                            <span className="fw-bold">{c}</span>
                                                                        </li>
                                                                    </ul>
                                            )
                                        })
                                    }
                                                                </div>
                                                                                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                    
                                                <div className="col-md-6 mt-2" >
                                                    <div className="card">
                                                    <div className="row card-header">
                                                        <h1>Statistics</h1>
                                                        </div>
                                            <div className="row card-body align-item-center">
                                                            <div className="row">
                                                            <div className="col-md-6">
                                                                    <span>Average Capacity</span>
                                                            </div>
                                                            <div className="col-md-6">
                                                                    <ul className="list-group">
                                                                        <li className="list-group-item list-group-item-event">
                                                                            <span className="fw-bold">{averageCapacity}</span>
                                                                        </li>
                                                                    </ul>
                                                            </div>
                                                           </div>
                                                           <div className="row">
                                                            <div className="col-md-6">
                                                                    <span>Total Capacity</span>
                                                            </div>
                                                            <div className="col-md-6">
                                                                    <ul className="list-group">
                                                                        <li className="list-group-item list-group-item-event">
                                                                            <span className="fw-bold">{totalCapacity}</span>
                                                                        </li>
                                                                    </ul>
                                                            </div>
                                                           </div>
                                                           <div className="row">
                                                            <div className="col-md-6">
                                                                    <span>Min Capacity</span>
                                                            </div>
                                                            <div className="col-md-6">
                                                                    <ul className="list-group">
                                                                        <li className="list-group-item list-group-item-event">
                                                                            <span className="fw-bold">{minCapacity}</span>
                                                                        </li>
                                                                    </ul>
                                                            </div>
                                                           </div>
                                                           <div className="row">
                                                            <div className="col-md-6">
                                                                    <span>Max Capacity</span>
                                                            </div>
                                                            <div className="col-md-6">
                                                                    <ul className="list-group">
                                                                        <li className="list-group-item list-group-item-event">
                                                                            <span className="fw-bold">{maxCapacity}</span>
                                                                        </li>
                                                                    </ul>
                                                            </div>
                                                           </div>
                                                           <div className="row">
                                                            <div className="col-md-6">
                                                                    <span>Total Capacity Count</span>
                                                            </div>
                                                            <div className="col-md-6">
                                                                    <ul className="list-group">
                                                                        <li className="list-group-item list-group-item-event">
                                                                            <span className="fw-bold">{countCapacity}</span>
                                                                        </li>
                                                                    </ul>
                                                            </div>
                                                           </div>
                                                    </div>
                                                </div>
                                    

                                </div>
                                </div>
                            </div>
                        </section>
                    </>
            }
        </>
    )
}

export default IndexPage;