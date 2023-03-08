import './index.css'
import { useEffect, useState } from 'react'
import axios from 'axios'
import { toast } from 'react-toastify'
import ProjectList from '../../component/ProjectList'
import { URL } from '../../config'
import { useNavigate } from 'react-router'
import { Link } from 'react-router-dom'
// import CreateProject from '../CreateProject'
// import { Navigate } from 'react-router'
// import ManagerHome from '../ManagerHome'

const ProjectsList = () => {

  const [employeeProjects, setEmployeeProjects] = useState([])
  const deptId = sessionStorage['DeptId']

  const searchProjects = () => {
    const url = `${URL}/manager/projectList/${deptId}`
    axios.get(url).then((response) => {
      const result = response.data
      if (result['status'] == 'success') {
        setEmployeeProjects(result['data'])
        console.log(employeeProjects);
        

      } else {
        toast.error(result['error'])
      }
    })
  }

  useEffect(() => {
    searchProjects()
  }, [])


  const navigate = useNavigate()


  const CreateProject = () => {
    navigate('/createProject')
  }

  return (
    <div>
      <div id="headerRow" className="row">
        <div className="col">
          <h2>Project List</h2>
        </div>
        <hr class="dropdown-divider" />
        <br />
        <div className="d-grid gap-2 d-md-flex justify-content-md-end">
          <button onClick={CreateProject} className="btn btn-primary me-md-2" type="button">+ Create New Project</button>
        </div>
      </div>
      <br />

      <div>
        <table className="table">
          <thead>
            <tr>
              <th scope="col">ID</th>
              <th scope="col">Project</th>
              <th scope="col">Start Date</th>
              <th scope="col">Due Date</th>
              <th scope="col">Status</th>

            </tr>
          </thead>
          <tbody>
            {employeeProjects.map((tempProject) => {
              return <ProjectList project={tempProject} />
            })}
            {/* <tr>
              <th scope="row">1</th>
              <td>Sample Project</td>
              <td>12/03/2022</td>
              <td>12/06/2022</td>
              <select className="form-select" aria-label="Default select example">
                <option selected>Select Status</option>
                <option value="1">In Progress</option>
                <option value="2">Completed</option>
              </select>
              <td><div className="row">
                <div className="d-grid gap-2 d-md-flex justify-content-md-end">
                  <button type="button" className="btn btn-primary">Add Task</button>
                </div></div></td>
            </tr> */}
          </tbody>
        </table>

        <div className="d-grid gap-2 d-md-flex justify-content-md-end">

          <div className="d-grid gap-2 d-md-flex justify-content-md-end">
            <button className="btn btn-primary me-md-2" type="button">Save</button>
            <Link to="/managerHome" class="btn btn-secondary">Back</Link>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ProjectsList;
