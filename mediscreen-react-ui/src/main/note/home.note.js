import React, { useContext, useEffect, useState } from "react";
import { makeStyles } from '@material-ui/core/styles';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import {listNote, removeNote} from "./note.rest";
import moment from "moment";
import { useHistory} from "react-router-dom";
import EditIcon from '@material-ui/icons/Edit';
import Button from "@material-ui/core/Button";
import DeleteIcon from '@material-ui/icons/Delete';

const useStyles = makeStyles({
    table: {
        width: "60%",
    },
});

function NotePatient() {
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
        listNote(data)
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
        history.push(`/edit-note/${data.id}`);
    }

    const remove = (data) => {
        removeNote(data.id)
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
            <TableContainer>
                <Table className={classes.table} aria-label="simple table" component={Paper}>
                    <TableHead>
                        <TableRow>
                            <TableCell>Note</TableCell>
                            <TableCell align="right">Actions</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody>
                        {list.map((row, i) => (
                            <TableRow key={i}>
                                <TableCell align="center">{row.notes}</TableCell>
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

export default NotePatient;
