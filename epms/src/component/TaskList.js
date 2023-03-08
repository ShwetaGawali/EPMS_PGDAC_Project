import { useLocation, useNavigate } from 'react-router'
import { useEffect, useState } from 'react'
import axios from 'axios'
import { toast } from 'react-toastify'
import { URL } from "../config"



const TaskList = (props) => {

    const { taskId, projectId, taskName, startDate, dueDate, status } = props.task
    // const [statusChange, setStatusChange] = useState(0)

    const markAsComplete = () => {

        // const updateStatus = updateStatus()

        const url = `${URL}/manager/markAsComplete/${taskId}`
        axios.patch(url).then((response) => {
            const result = response.data
            if (result['status'] == 'success') {
                toast.success(`${taskName} Completed!`)
                // setStatusChange(previousState => previousState +1)
            } else {
                toast.error(result['error'])
            }
        })
        window.location.reload(true)

    }


    return (

        <tr>
            <td>{taskId}</td>
            <td>{projectId}</td>
            <td>{taskName}</td>
            <td>{startDate}</td>
            <td>{dueDate}</td>
            <td>{status}</td>
            <td>
                {status !== 'Completed' && (
                    <div className="mb-3">
                        <button onClick={markAsComplete} className="btn btn-primary">Mark as complete</button>
                    </div>

                )}
            </td>
        </tr>

    )
}

export default TaskList
