
import axios from 'axios';

export const sendEmailToGroup = async (groupID) => {
    try {
        const response = await axios.post('http://cs431-01.cs.rutgers.edu:8080/email/force-email-to-users-of-group', null, {
            params: { groupID: groupID }
        });
        console.log(response.data);
        return response.data;
    } catch (error) {
        console.error('Error sending emails to group:', error);
    }
};

export const sendEmailToUserInGroup = async (groupID, userID) => {
    try {
        const response = await axios.post('http://cs431-01.cs.rutgers.edu:8080/email/force-email-to-this-user-in-group', null, {
            params: { groupID: groupID, userID: userID }
        });
        console.log(response.data);
        return response.data;
    } catch (error) {
        console.error('Error sending email to specific user in group:', error);
    }
};
