INSERT INTO bot_answers (tg_id, question_id, answer)
VALUES (10, 3, 'example10')
ON CONFLICT DO NOTHING;

