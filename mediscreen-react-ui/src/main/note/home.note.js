import React, { useContext, useEffect, useState, forwardRef } from "react";
import {listNote, removeNote} from "./note.rest";
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
import MaterialTable from "material-table";
import {listPatient} from "../patient/home.patient.rest";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogContent from "@material-ui/core/DialogContent";
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


function NotePatient() {
    let datas = [];
    const [currentItem, setCurrentItem] = useState([]);
    const [patients, setPatients] = useState([]);
    const [load,setLoad] = useState(true);
    let history = useHistory();
    const [open, setOpen] = useState(false);

    const handleClickOpen = (item) => {
        setCurrentItem(item);
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };
    const [state, setState] = useState({
        columns: [
            { title: 'Note', field: 'notes',
                render: (rowData) =>
                    rowData && (
                        <div>
                            {rowData.notes}
                        </div>
                    )
            },
            { title: 'Patient', field: 'birthday',
                render: (rowData) =>
                    rowData && (
                        <div>
                            {getPatient(rowData)}
                        </div>
                    )
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

    const getPatient = (row) => {
       const findPatient = datas.find((item) => item.id == row.idPatient );
       return findPatient ? findPatient.name : null;
    }

    useEffect(() => {
       if(load){
           setLoad(false);
           loadPatient();
       }
    });


    const loadPatient = () => {
        listPatient()
            .then((response) => {
                setPatients(response.data);
                datas = response.data;
                loadList();
            })
            .catch((error) => {
            })
            .finally((response) => {
            });
    };

    const loadList = (data) => {
        listNote(data)
            .then((response) => {
                setState({ ...state, ["data"]: response.data});
            })
            .catch((error) => {
            })
            .finally((response) => {
            });
    };

    const edit = (data) => {
        history.push(`/edit-note/${data.id}`);
    }

    const remove = () => {
        handleClose();
        removeNote(currentItem.id)
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
               <Button onClick={()=> history.push("/add-note")} variant="contained" color="primary">
                   Add Note
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
                    title="Liste des Notes Patient"
                    columns={state.columns}
                    data={state.data}
                    icons={tableIcons}
                />
        </div>
    );
}

export default NotePatient;
