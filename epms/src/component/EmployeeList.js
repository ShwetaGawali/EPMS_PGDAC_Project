import { useLocation, useNavigate } from 'react-router'
import { useEffect, useState } from 'react'
import axios from 'axios'
import { toast } from 'react-toastify'
import { URL } from "../config"
import TaskList from './TaskList'



const EmployeeList = (props) => {

    const { empId, name, taskList } = props.employee
    const [unassignedTasks, setUnassignedTasks] = useState([])
    const [taskId, setTaskId] = useState()

    const availableTasks = () => {

        const url = `${URL}/manager/unassignedTasks`
        axios.get(url).then((response) => {
            const result = response.data
            if (result['status'] == 'success') {
                setUnassignedTasks(result['data'])

            } else {
                toast.error(result['error'])
            }
        })

    }

    const assignTaskToEmployee = () => {

        const body = {
            "status": "In-progress",
            "employee": {
                empId
            }
        }
        const url = `${URL}/manager/assignTask/${taskId}`
        axios.patch(url, body).then((response) => {

            const result = response.data
            if (result['status'] == 'success') {

                console.log(unassignedTasks);
                console.log(typeof taskId);
                
                const assignedTask = unassignedTasks.find(t=> t.taskId === parseInt(taskId))
                console.log(assignedTask);
                
                toast.success(`Task ${assignedTask.taskName} assigned to ${name}`)

            } else {
                toast.error(result['error'])
            }
        })

    }



    return (

        <tr>
            <th scope="row">{empId}</th>
            <td>{name}</td>
            <td>
                <ul>
                    {
                        taskList.map((task) => {
                            return <li>{task.taskName}</li>
                        })
                    }
                </ul>
            </td>
            <td>
                <select
                    onChange={(e) => {
                        setTaskId(e.target.value)
                    }} onClick={availableTasks} style={{ width: "150px" }} className="form-select" aria-label="Default select example">
                    <option selected>Select task</option>

                    {
                        unassignedTasks.map((task) => {
                            return <option value={task.taskId}>{task.taskName}</option>
                        })
                    }
                </select>
            </td>
            <td>
                <div className="mb-3">
                    <button onClick={assignTaskToEmployee} className="btn btn-primary">Assign</button>
                </div>
            </td>
        </tr>

    )
}

export default EmployeeList