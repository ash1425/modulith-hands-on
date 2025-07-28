import './App.css'
import useSWR from "swr";
import fetcher from "./fetcher.ts";
import type {Product} from "./Products.ts";
import Cart from "./cart/Cart.tsx";

const addToCart = async (productId: string) => {
    let cartId = localStorage.getItem("cartId");
    if (cartId) {
        await fetch(`/carts/${cartId}/items/${productId}?quantity=1`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        });
    } else {
        const cartId = (await (await fetch('/carts', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        })).json()).id;
        localStorage.setItem("cartId", cartId);

        await fetch(`/carts/${cartId}/items/${productId}?quantity=1`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        });
    }
}

const App = () => {
    const {data, error, isLoading} = useSWR<Product[]>("/products", fetcher)
    if (error) return <div>failed to load</div>
    if (isLoading) return <div>loading...</div>
    return (
        <div>
            <Cart/>
            <hr/>
            {data?.map(product => (
                <div key={product.productId}>
                    <h2>{product.productName}</h2>
                    <p>{product.productId}</p>
                    <p>Price: ${product.price.toFixed(2)}</p>
                    <p>Stock: {product.stock}</p>
                    <button onClick={() => addToCart(product.productId)}>Add to cart</button>
                </div>
            )) || <div>No products available</div>}
        </div>
    )
};

export default App
