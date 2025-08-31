-- Initial data (INSERT INTO statements) for PostgreSQL Database

-- Insert data for the about_me page 
INSERT INTO about_me (id, content, author) VALUES
(
  1,
  'Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat. Duis autem vel eum iriure dolor in hendrerit in vulputate velit esse molestie consequat, vel illum dolore eu feugiat nulla facilisis at vero eros et accumsan et iusto odio dignissim qui blandit praesent luptatum zzril delenit augue duis dolore te feugait nulla facilisi. Nam liber tempor cum soluta nobis eleifend option congue nihil imperdiet doming id quod mazim placerat facer possim assum.<br/><br/>Typi non habent claritatem insitam; est usus legentis in iis qui facit eorum claritatem. Investigationes demonstraverunt lectores legere me lius quod ii legunt saepius. Claritas est etiam processus dynamicus, qui sequitur mutationem consuetudium lectorum. Mirum est notare quam littera gothica, quam nunc putamus parum claram, anteposuerit litterarum formas humanitatis per seacula quarta decima et quinta decima. Eodem modo typi, qui nunc nobis videntur parum clari, fiant sollemnes in futurum.',
  'Vivianne Russell'
);

-- Insert data for blog posts
INSERT INTO post (title, type, content, image_path, created_at) VALUES
(
  'Essential skills for a happy life!',
  'blog',
  '<p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam erat volutpat. Ut wisi enim ad minim veniam, quis nostrud exerci tation ullamcorper suscipit lobortis nisl ut aliquip ex ea commodo consequat.</p><p>At eam doctus oportere, eam feugait delectus ne. Quo cu vulputate persecuti. Eum ut natum possim comprehensam, habeo dicta scaevola eu nec. Ea adhuc reformidans eam. Pri dolore epicuri eu, ne cum tantas fierent instructior. Pro ridens intellegam ut, sea at graecis scriptorem eloquentiam.</p><p>Per an labitur lucilius ullamcorper, esse erat ponderum ad vim. Possim laoreet suscipit ex pri, vix numquam eruditi feugait in. Nec maluisset complectitur te, at sea decore semper. Falli numquam perpetua sea et, tibique repudiandae an pro. Ut his solum persius postulant. Soluta nemore debitis ne eos, cum an scripta pericula partiendo.</p>',
  'i2.jpg',
  '2015-11-16'::DATE
),
(
  'You''ve gotta dance',
  'quote',
  'You''ve gotta dance like there''s nobody watching,<br/>Love like you''ll never be hurt,<br/>Sing like there''s nobody listening,<br/>And live like it''s heaven on earth. - William W. Purkey',
  '',
  '2015-11-16'::DATE
),
(
  'Photo',
  'photo',
  '',
  'i1.jpg',
  '2015-11-16'::DATE
);

-- Insert data for social media links
INSERT INTO social (name, icon) VALUES
  ('Facebook', 'fb.jpg'),
  ('Twitter', 'tw.jpg'),
  ('Google+', 'gg.jpg');

-- Initialize the page view counter
INSERT INTO total_views (id, view_count) VALUES (1, 1857); -- Start with a realistic number
