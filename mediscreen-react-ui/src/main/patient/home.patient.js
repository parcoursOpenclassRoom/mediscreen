import React, { useContext, useEffect, useState } from "react";
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {listPatient, removePatient} from "./home.patient.rest";
import moment from "moment";
import { useHistory} from "react-router-dom";
import EditIcon from '@material-ui/icons/Edit';
import Button from "@material-ui/core/Button";
import DeleteIcon from '@material-ui/icons/Delete';

const useStyles = makeStyles({
    table: {
        minWidth: 650,
    },
});

function HomePatient() {
    const classes = useStyles();
    const [list,setList] = useState([]);
    const [load,setLoad] = useState(true);
    let history = useHistory();

    useEffect(() => {
       if(load){
           setLoad(false);
           loadList();
       }
    });

    const loadList = (data) => {
        listPatient(data)
            .then((response) => {
                setList(response.data);
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

    const remove = (data) => {
        removePatient(data.id)
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
            <TableContainer component={Paper}>
                <Table className={classes.table} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell>Nom</TableCell>
                            <TableCell align="right">Naissance</TableCell>
                            <TableCell align="right">Genre</TableCell>
                            <TableCell align="right">Adresse</TableCell>
                            <TableCell align="right">Téléphone</TableCell>
                            <TableCell align="right">Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {list.map((row, i) => (
                            <TableRow key={i}>
                                <TableCell component="th" scope="row">
                                    {`${row.name} ${row.firstName}`}
                                </TableCell>
                                <TableCell align="right">{formatStandar(row.birthday)}</TableCell>
                                <TableCell align="right">{row.sex}</TableCell>
                                <TableCell align="right">{row.address}</TableCell>
                                <TableCell align="right">{row.phone}</TableCell>
                                <TableCell align="right">
                                    <Button onClick={()=> edit(row) } color="primary">
                                        <EditIcon/>
                                    </Button>
                                    <Button onClick={()=> remove(row) } color="primary">
                                        <DeleteIcon/>
                                    </Button>
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
        </div>
    );
}

export default HomePatient;
