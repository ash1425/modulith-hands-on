import useSWR from "swr";
import fetcher from "../fetcher.ts";

export type Cart = {
    id: string;
    items: Array<{
        id: string;
        product: {
            productId: string;
            productName: string;
            price: number;
            stock: number;
        };
        quantity: number;
    }>;
    total: number;
}

const Cart = () => {
    if (localStorage.getItem("cartId")) {
        const {data: cart, error, isLoading} = useSWR<Cart>(`/carts/${localStorage.getItem("cartId")}`, fetcher)
        if (error) return <div>Failed to load cart</div>;
        if (isLoading) return <div>Loading cart...</div>;
        if (cart && cart.items.length > 0) {
            return (
                <div>
                    <h3>Shopping Cart</h3>
                    <ul>
                        {cart.items.map(item => (
                            <li key={item.id}>
                                {item.product.productName} - Quantity: {item.quantity} - Price:
                                ${(item.product.price * item.quantity).toFixed(2)}
                            </li>
                        ))}
                    </ul>
                    <p>Total: ${cart.total.toFixed(2)}</p>
                </div>
            );
        }
    } else {
        return (
            <div>
                <h3>Shopping Cart</h3>
                <p>Your cart is empty.</p>
            </div>
        );
    }
};

export default Cart;