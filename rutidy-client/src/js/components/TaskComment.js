import React, { useState, useEffect, useRef} from 'react';
import '../../css/ChoreComment.css';
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
    const commentsEndRef = useRef(null);

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

    useEffect(() => {
        commentsEndRef.current?.scrollIntoView({ behavior: "smooth" });
    }, [comments]); // Dependency array includes 'comments' to trigger effect when it updates
    
    

    const handleAddComment = async (event) => {
        if(event){
            event.preventDefault();
        }
        
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
            setCharCount(0); // Reset the character count
            setNewCommentText(''); // Clear the input field
        } catch (error) {
            console.error('Error adding comment:', error);
        }
    };



    const startEditing = (comment) => {
        setEditingComment(comment);
        setEditedCommentText(comment.comment);
    };

    const formatDateTime = (dateString) => {
        const options = { hour: 'numeric', minute: 'numeric', hour12: true };
        const date = new Date(dateString);
        const timeString = date.toLocaleTimeString('en-US', options);
        const dateString2 = date.toLocaleDateString('en-US', { day: '2-digit', month: '2-digit', year: 'numeric' });
        return `${timeString}, ${dateString2}`;
    }

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

    const handleKeyDown = (event) => {
        if (event.key === 'Enter' && !event.shiftKey) { // Checks if Enter key is pressed without Shift
            event.preventDefault(); // Prevents the default action of adding a new line
            handleAddComment(); // Calls the function to handle adding a comment
        }
    };

    return (
        <div className="container">
            <h2>Task Comments</h2>
            <div className="comments-container">
                {comments.map((comment,index) => (
                    <div 
                        key={comment.commentID} 
                        className={`comment ${parseInt(comment.author.userID) !== parseInt(currentUserID) ? 'other-comment' : ''}`}
                        ref={index === comments.length-1 ? commentsEndRef : null}>
                        <div className="comment-header">
                            <span className="comment-author">{comment.author.name}</span>
                            <span className="comment-time">{formatDateTime(comment.date)}</span>
                        </div>
                        <div className="comment-body">{comment.comment}</div>
                        {parseInt(comment.author.userID) === parseInt(currentUserID) && (
                            <div className="comment-options">
                                <span onClick={() => {handleDeleteComment(comment.commentID)}}>|</span>
                            </div>
                        )}
                    </div>
                ))}
            </div>


            <form onSubmit={handleAddComment}>
            <textarea 
                className="text-entry"
                value={newCommentText} 
                onChange={handleInputChange}
                onKeyDown={handleKeyDown}
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
