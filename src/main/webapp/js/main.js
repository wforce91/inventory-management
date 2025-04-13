const form = document.getElementById('productForm');
const list = document.getElementById('productList');
const cancelUpdate = document.getElementById('cancelUpdate');
const nameInput = document.getElementById('name');
const quantityInput = document.getElementById('quantity');
const idInput = document.getElementById('productId');

form.addEventListener('submit', async (e) => {
  e.preventDefault();
  const id = idInput.value;
  const name = nameInput.value;
  const quantity = quantityInput.value;

  const method = id ? 'PUT' : 'POST';
  const url = id
    ? `/inventory-management/api/products/${id}`
    : '/inventory-management/api/products';

  await fetch(url, {
    method,
    headers: { 'Content-Type': 'application/json' },
    body: JSON.stringify({ name, quantity })
  });

  form.reset();
  idInput.value = '';
  loadProducts();
});

cancelUpdate.addEventListener('click', () => {
  form.reset();
  idInput.value = '';
});

async function loadProducts() {
  const res = await fetch('/inventory-management/api/products');
  const products = await res.json();

  list.innerHTML = '';
  products.forEach(p => {
    const item = document.createElement('li');
    item.className = 'list-group-item';

    const text = document.createElement('span');
    text.textContent = `${p.name} - ${p.quantity}`;

    const actions = document.createElement('div');
    actions.className = 'btn-group';

    const edit = document.createElement('button');
    edit.className = 'btn btn-sm btn-warning';
    edit.textContent = 'Edit';
    edit.onclick = () => {
      nameInput.value = p.name;
      quantityInput.value = p.quantity;
      idInput.value = p.id;
    };

    const del = document.createElement('button');
    del.className = 'btn btn-sm btn-danger';
    del.textContent = 'Delete';
    del.onclick = async () => {
      await fetch(`/inventory-management/api/products/${p.id}`, {
        method: 'DELETE'
      });
      loadProducts();
    };

    actions.appendChild(edit);
    actions.appendChild(del);

    item.appendChild(text);
    item.appendChild(actions);

    list.appendChild(item);
  });
}

loadProducts();
