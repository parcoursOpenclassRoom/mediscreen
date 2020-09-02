import React, { useEffect, useState, forwardRef } from "react";
import { makeStyles } from '@material-ui/core/styles';
import MaterialTable from 'material-table';
import {listPatient, removePatient} from "./home.patient.rest";
import moment from "moment";
import { useHistory} from "react-router-dom";
import EditIcon from '@material-ui/icons/Edit';
import Button from "@material-ui/core/Button";
import DeleteIcon from '@material-ui/icons/Delete';

import ArrowDownward from '@material-ui/icons/ArrowDownward';
import ChevronLeft from '@material-ui/icons/ChevronLeft';
import ChevronRight from '@material-ui/icons/ChevronRight';
import Clear from '@material-ui/icons/Clear';
import FilterList from '@material-ui/icons/FilterList';
import FirstPage from '@material-ui/icons/FirstPage';
import LastPage from '@material-ui/icons/LastPage';
import Search from '@material-ui/icons/Search';
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogActions from "@material-ui/core/DialogActions";

const tableIcons = {
    Clear: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
    Filter: forwardRef((props, ref) => <FilterList {...props} ref={ref} />),
    FirstPage: forwardRef((props, ref) => <FirstPage {...props} ref={ref} />),
    LastPage: forwardRef((props, ref) => <LastPage {...props} ref={ref} />),
    NextPage: forwardRef((props, ref) => <ChevronRight {...props} ref={ref} />),
    PreviousPage: forwardRef((props, ref) => <ChevronLeft {...props} ref={ref} />),
    ResetSearch: forwardRef((props, ref) => <Clear {...props} ref={ref} />),
    Search: forwardRef((props, ref) => <Search {...props} ref={ref} />),
    SortArrow: forwardRef((props, ref) => <ArrowDownward {...props} ref={ref} />),
};


function HomePatient() {
    const [load,setLoad] = useState(true);
    const [currentItem, setCurrentItem] = useState([]);
    const [open, setOpen] = useState(false);
    let history = useHistory();
    const [state, setState] = useState({
        columns: [
            { title: 'Nom', field: 'name',
                render: (rowData) =>
                    rowData && (
                        <div>
                            {`${rowData.name} ${rowData.firstName}`}
                        </div>
                    )
            },
            { title: 'Naissance', field: 'birthday',
                render: (rowData) =>
                    rowData && (
                        <div>
                            {formatStandar(rowData.birthday)}
                        </div>
                    )
            },
            { title: 'Genre', field: 'sex' },
            { title: 'Adresse', field: 'address'},
            {
                title: 'Téléphone',
                field: 'phone'
            },
            {
                title: "Actions",
                field: "internal_action",
                editable: false,
                render: (rowData) =>
                    rowData && (
                       <div>
                           <Button onClick={()=> edit(rowData) } color="primary">
                               <EditIcon/>
                           </Button>
                           <Button onClick={()=> handleClickOpen(rowData) } color="primary">
                               <DeleteIcon/>
                           </Button>
                       </div>
                    )
            }
        ],
        data: [],
    });

    const handleClickOpen = (item) => {
        setCurrentItem(item);
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };

    useEffect(() => {
       if(load){
           setLoad(false);
           loadList();
       }
    });

    const loadList = (data) => {
        listPatient(data)
            .then((response) => {
                setState({ ...state, ["data"]: response.data });
                //setList(response.data);
            })
            .catch((error) => {
            })
            .finally((response) => {
            });
    };

     function formatStandar(date) {
        if (date != null) {
            return moment(date).format("dddd Do MMMM YYYY");
        }
        return null;
    }

    const edit = (data) => {
        history.push(`/edit-patient/${data.id}`);
    }

    const remove = () => {
        handleClose();
        removePatient(currentItem.id)
            .then((response) => {
                loadList();
            })
            .catch((error) => {
            })
            .finally((response) => {
            });
    }

    return (
        <div>
           <div style={{margin:10}}>
               <Button onClick={()=> history.push("/add-patient")} variant="contained" color="primary">
                   Add Patient
               </Button>
               <br/>
           </div>
            <Dialog
                open={open}
                onClose={handleClose}
                aria-labelledby="alert-dialog-title"
                aria-describedby="alert-dialog-description"
            >
                <DialogTitle id="alert-dialog-title">{"Confirmation"}</DialogTitle>
                <DialogContent>
                    <DialogContentText id="alert-dialog-description">
                        Voulez-vous vraiment supprimer l'élément ?
                    </DialogContentText>
                </DialogContent>
                <DialogActions>
                    <Button onClick={handleClose} color="primary">
                        NON
                    </Button>
                    <Button onClick={()=>remove()} color="primary" autoFocus>
                        OUI
                    </Button>
                </DialogActions>
            </Dialog>
            <MaterialTable
                title="Liste des Patients"
                columns={state.columns}
                data={state.data}
                icons={tableIcons}
            />
        </div>
    );
}

export default HomePatient;
