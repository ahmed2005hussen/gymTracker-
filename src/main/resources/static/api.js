const HADIDY_API_URL = '';
const HADIDY_AUTH_KEY = 'hadidy_basic_auth';

function setAuth(username, password) {
  sessionStorage.setItem(HADIDY_AUTH_KEY, btoa(`${username}:${password}`));
  sessionStorage.setItem('hadidy_username', username);
}

function clearAuth() {
  sessionStorage.removeItem(HADIDY_AUTH_KEY);
  sessionStorage.removeItem('hadidy_username');
}

function currentUsername() {
  return sessionStorage.getItem('hadidy_username') || '';
}

function requireAuth() {
  if (!sessionStorage.getItem(HADIDY_AUTH_KEY)) {
    window.location.replace('Login.html');
    throw new Error('Please sign in first.');
  }
}

async function api(path, options = {}) {
  const auth = sessionStorage.getItem(HADIDY_AUTH_KEY);
  const headers = new Headers(options.headers || {});
  if (auth) headers.set('Authorization', `Basic ${auth}`);
  if (options.body && !headers.has('Content-Type')) headers.set('Content-Type', 'application/json');
  let response;
  try {
    response = await fetch(`${HADIDY_API_URL}${path}`, { ...options, headers });
  } catch {
    throw new Error('Cannot reach the backend. Start Spring Boot on http://localhost:8080.');
  }
  const contentType = response.headers.get('content-type') || '';
  const body = contentType.includes('application/json') ? await response.json() : await response.text();
  if (!response.ok) {
    if (response.status === 401) clearAuth();
    throw new Error(typeof body === 'string' && body ? body : 'Request failed.');
  }
  return body;
}

function logout() { clearAuth(); window.location.href = 'Login.html'; }
function showApiError(error) { alert(error.message || 'Something went wrong. Please try again.'); }

function standardizeSidebar() {
  const pages = [
    ['Dashboard.html', '⌂', 'Overview'],
    ['Workouts.html', '◈', 'Workouts'],
    ['Diet.html', '◉', 'Nutrition'],
    ['Supplements.html', '✦', 'Supplements']
  ];
  const current = window.location.pathname.split('/').pop() || 'Dashboard.html';
  document.querySelectorAll('.sidebar').forEach(sidebar => {
    const links = pages.map(([href, icon, label]) => `<a href="${href}" class="sidebar-link ${current === href ? 'active' : ''}"><span class="icon">${icon}</span>${label}</a>`).join('');
    sidebar.innerHTML = `<div class="sidebar-section"><div class="sidebar-label">Main</div>${links}</div><div class="sidebar-section"><div class="sidebar-label">Connect</div><a href="Profile.html" class="sidebar-link ${current === 'Profile.html' ? 'active' : ''}"><span class="icon">◎</span>Profile</a></div><div class="sidebar-section"><a href="Login.html" class="sidebar-link" onclick="logout()"><span class="icon">←</span>Log out</a></div>`;
    sidebar.style.visibility = 'visible';
  });
  document.querySelectorAll('a[href="Community.html"], a[href="Progress.html"]').forEach(link => {
    const listItem = link.closest('li');
    (listItem || link).remove();
  });
  document.querySelectorAll('.nav-links').forEach(nav => nav.style.visibility = 'visible');
}

standardizeSidebar();
