import React, { useState, useEffect  } from 'react';
import '../../css/Chores.css';
import { 
    createTaskComment, 
    getAllTaskComments, 
    updateTaskComment, 
    deleteTaskComment, 
    buildCommentDTO, 
    buildCommentUpdateDTO 
} from '../api/commentAPI'; // Adjust the import path as needed

const TaskCommentsComponent = React.memo(({ taskID, currentUserID }) => {
    const [comments, setComments] = useState([]);
    const [newCommentText, setNewCommentText] = useState('');
    const [editingComment, setEditingComment] = useState(null);
    const [editedCommentText, setEditedCommentText] = useState('');
    const [charCount, setCharCount] = useState(0);

    useEffect(() => {
        const fetchComments = async () => {
            try {
                const response = await getAllTaskComments(taskID);
                if (response.message === "Success") {
                    setComments(response.object); // Assuming 'object' is the array of comments
                } else {
                    console.error('Error fetching comments:', response.message);
                }
            } catch (error) {
                console.error('Error fetching comments:', error);
            }
        };

        fetchComments();
    }, [taskID]);

    

    const handleAddComment = async (event) => {
        event.preventDefault();
        if (!newCommentText.trim() || newCommentText.length > 255) return;
        try {
            const newCommentDTO = buildCommentDTO(taskID, currentUserID, newCommentText);
            const response = await createTaskComment(newCommentDTO);
    
            if (response.message === "Success" && response.object) {
                // Extract the actual comment object from response and add to the state
                setComments(prevComments => [...prevComments, response.object]);
            } else {
                // Handle the case where the response is not as expected
                console.error('Failed to add comment:', response.message);
            }
            
            setNewCommentText(''); // Clear the input field
        } catch (error) {
            console.error('Error adding comment:', error);
        }
    };

    const startEditing = (comment) => {
        setEditingComment(comment);
        setEditedCommentText(comment.comment);
    };


    const handleEditComment = async () => {
        if (!editedCommentText.trim()) return;
        try {
            const updatedDTO = buildCommentUpdateDTO(editedCommentText, currentUserID, taskID, editingComment.taskCommentID);
            const updatedComment = await updateTaskComment(updatedDTO);
            setComments(comments.map(comment => 
                comment.taskCommentID === updatedComment.taskCommentID ? updatedComment : comment
            ));
            setEditingComment(null);
            setEditedCommentText('');
        } catch (error) {
            console.error('Error updating comment:', error);
        }
    };

    const handleDeleteComment = async (commentID) => {
        try {
            await deleteTaskComment(commentID);
            setComments(comments.filter(comment => comment.commentID !== commentID));
        } catch (error) {
            console.error('Error deleting comment:', error);
        }
    };

    const handleInputChange = (e) => {
        const text = e.target.value;
        setNewCommentText(text);
        setCharCount(text.length); // Update character count as the user types
    };

    return (
        <div className="container">
            <h2>Task Comments</h2>
            {comments.map(comment => (
                <div key={comment.task.taskID}>
                    <p>
                        <strong>{comment.author.name}:</strong>
                        <span className="comment-text">{comment.comment}</span>
                    </p>
    
                    {parseInt(comment.author.userID) === parseInt(currentUserID) && (
                        <div>
                            <button onClick={() => handleDeleteComment(comment.commentID)}>Delete</button>
                        </div>
                    )}
                </div>
            ))}


            <form onSubmit={handleAddComment}>
                <textarea 
                    value={newCommentText} 
                    onChange={handleInputChange}
                    placeholder="Write a new comment..."
                />
                <div>
                  {charCount}/255 characters
                </div>
                <button type="submit">Add Comment</button>
            </form>
        </div>
    );
});

export default TaskCommentsComponent;
