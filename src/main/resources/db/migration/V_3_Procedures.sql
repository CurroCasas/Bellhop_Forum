CREATE PROCEDURE paginate (topic_id INT, offset INT, limi INT)
READS SQL DATA
BEGIN
PREPARE stmt FROM "SELECT * from Message WHERE topic_id=? ORDER by time ASC LIMIT ?, ?;";
EXECUTE stmt USING @topic_id, @offset, @limi;
END;