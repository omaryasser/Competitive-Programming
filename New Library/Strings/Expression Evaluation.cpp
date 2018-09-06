bool delim (char c) {
    return c == ' ';
}

bool is_op (char c) {
    return c=='+' || c=='-' || c=='*' || c=='/' || c=='%';
}

int priority (char op) {
    if (op < 0)
        return 4; // op == -'+' || op == -'-'
    return
            op == '+' || op == '-' ? 1 :
            op == '*' || op == '/' || op == '%' ? 2 :
            -1;
}

void process_op (vector<int> & st, char op) {
    if (op < 0) {
        int l = st.back();  st.pop_back();
        switch (-op) {
            case '+':  st.push_back (l);  break;
            case '-':  st.push_back (-l);  break;
        }
    }
    else {
        int r = st.back();  st.pop_back();
        int l = st.back();  st.pop_back();
        switch (op) {
            case '+':  st.push_back (l + r);  break;
            case '-':  st.push_back (l - r);  break;
            case '*':  st.push_back (l * r);  break;
            case '/':  st.push_back (l / r);  break;
            case '%':  st.push_back (l % r);  break;
        }
    }
}

bool isunary (char c) {
    return c == '+' || c == '-';
}

int calc (string & s) {
    bool may_unary = true;
    vector<int> st;
    vector<char> op;
    for (size_t i=0; i<s.length(); ++i)
        if (!delim (s[i]))
            if (s[i] == '(') {
                op.push_back ('(');
                may_unary = true;
            }
            else if (s[i] == ')') {
                while (op.back() != '(')
                    process_op (st, op.back()),  op.pop_back();
                op.pop_back();
                may_unary = false;
            }
            else if (is_op (s[i])) {
                char curop = s[i];
                if (may_unary && isunary (curop))  curop = -curop;

                while (!op.empty() && (
                        curop >= 0 && priority(op.back()) >= priority(curop)
                        || curop < 0 && priority(op.back()) > priority(curop))
                        ) {
                    process_op(st, op.back()), op.pop_back();
                }
                op.push_back (curop);
                may_unary = true;
            }
            else {
                string operand;
                while (i < s.length() && isalnum (s[i]))
                    operand += s[i++];
                --i;
                if (isdigit (operand[0]))
                    st.push_back (atoi (operand.c_str()));
//                else
//                    st.push_back (get_variable_val (operand));
                may_unary = false;
            }
    while (!op.empty())
        process_op (st, op.back()),  op.pop_back();
    return st.back();
}
